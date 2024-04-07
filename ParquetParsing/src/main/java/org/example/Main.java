package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.avro.generic.GenericRecord;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.avro.AvroParquetReader;
import org.apache.parquet.hadoop.ParquetReader;
import org.apache.parquet.hadoop.util.HadoopInputFile;
import org.apache.parquet.io.InputFile;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        String filePath = "./cities.parquet";

        // Open the Parquet file as an AvroParquetReader
        InputFile inputFile = HadoopInputFile.fromPath(new Path(filePath), new Configuration());
        ParquetReader<GenericRecord> parquetReader = AvroParquetReader.<GenericRecord>builder(inputFile).build();

        // ObjectMapper for JSON processing
        ObjectMapper objectMapper = new ObjectMapper();

        GenericRecord record;
        while ((record = parquetReader.read()) != null) {
            String recordSchema = record.getSchema().toString(true);

            // Skip the Avro schema by directly accessing the record's fields
            String continentString = record.get("continent").toString();

            GenericRecord countryRecord = (GenericRecord) record.get("country");
            String countryString = countryRecord.toString();

            String nameString = countryRecord.get("name").toString();

            @SuppressWarnings("unchecked") // 타입 경고 억제
            List<GenericRecord> cityRecord = (List<GenericRecord>) countryRecord.get("city");
            for(GenericRecord city : cityRecord) {
                System.out.print(city.get("array_element") + ", ");
            }
            System.out.println("----------------------------------------");

            Country country = objectMapper.readValue(countryString, Country.class);
            Cities cities = new Cities();
            cities.continent = continentString;
            cities.country = country;
//            System.out.println(cities);
        }

        parquetReader.close();

    }
}
