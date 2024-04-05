package org.example;

import org.apache.avro.generic.GenericRecord;
import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.avro.AvroParquetReader;
import org.apache.parquet.hadoop.ParquetReader;
import org.apache.parquet.hadoop.util.HadoopInputFile;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.io.InputFile;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

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
            // Skip the Avro schema by directly accessing the record's fields
            String continentString = record.get("continent").toString();
            String countryString = record.get("country").toString();

            Country country = objectMapper.readValue(countryString, Country.class);
            Cities cities = new Cities();
            cities.continent = continentString;
            cities.country = country;

            System.out.println(cities);
        }

        parquetReader.close();

        // ParquetToJson parquetToJson = new ParquetToJson(filePath);
        // parquetToJson.convertToJson();

    }
}
