package org.example;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.example.data.simple.SimpleGroup;
import org.apache.parquet.example.data.simple.convert.GroupRecordConverter;
import org.apache.parquet.hadoop.ParquetFileReader;
import org.apache.parquet.hadoop.ParquetReader;
import org.apache.parquet.hadoop.example.GroupReadSupport;
import org.apache.parquet.hadoop.util.HadoopInputFile;
import org.apache.parquet.io.InputFile;
import org.apache.parquet.schema.MessageType;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ParquetToJson {

    String filePath;

    public ParquetToJson(String filePath) {
        this.filePath = filePath;
    }

    public void convertToJson() throws IllegalArgumentException, IOException {
        // Code to convert Parquet file to JSON
        InputFile inputFile = HadoopInputFile.fromPath(new Path(filePath), new Configuration());
        try (ParquetFileReader reader = ParquetFileReader.open(inputFile)) {
            MessageType schema = reader.getFooter().getFileMetaData().getSchema();
            GroupReadSupport groupReadSupport = new GroupReadSupport();
            ParquetReader<Group> groupReader = ParquetReader.builder(groupReadSupport, new Path(filePath)).build();

            GroupRecordConverter converter = new GroupRecordConverter(schema);
            ObjectMapper objectMapper = new ObjectMapper();

            Group group;
            while ((group = groupReader.read()) != null) {
                SimpleGroup simpleGroup = (SimpleGroup) converter.getCurrentRecord();
                String json = objectMapper.writeValueAsString(simpleGroup);
                System.out.println(json);

            }

        } catch (Exception e) {
            System.out.println("dead");
            System.out.println(e.getMessage());
        }

    }
}
