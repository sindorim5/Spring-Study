package org.example;

import java.io.IOException;

import org.apache.avro.generic.GenericRecord;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.avro.AvroParquetReader;
import org.apache.parquet.hadoop.ParquetReader;
import org.apache.parquet.hadoop.util.HadoopInputFile;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ParquetToJson {

    String filePath;

    public ParquetToJson(String filePath) {
        this.filePath = filePath;
    }

    public void convertToJson() throws IllegalArgumentException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Parquet 파일 읽기
        ParquetReader<GenericRecord> reader = AvroParquetReader
                .<GenericRecord>builder(HadoopInputFile.fromPath(new Path(filePath), new Configuration()))
                .build();

        GenericRecord record;
        while ((record = reader.read()) != null) {
            // GenericRecord를 JSON 문자열로 변환
            String jsonString = objectMapper.writeValueAsString(record);
            System.out.println(jsonString);
        }
    }
}
