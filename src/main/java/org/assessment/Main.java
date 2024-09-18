package org.assessment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.StringValue;
import model.Data;
import org.assessment.proto.Stock;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        var main = new Main();
    }

    public Main () throws IOException {
        System.out.println("Hello world!");
        Stock.Laptop laptop = Stock.Laptop.newBuilder()
                .setId("L")
                .setBrand("Leno")
                .setName(StringValue.of("X1"))
                .setDescription(Stock.NullableString.newBuilder().setValue("").build())
                .setPrice(Stock.NullableDouble.newBuilder().setValue(0).build())
                .build();

        System.out.println(laptop.getPrice());
        var bytes = laptop.toByteArray();
        System.out.println(bytes);

        Stock.Laptop a = Stock.Laptop.parseFrom(bytes);
        System.out.println(a.getDescription().getValue());
        System.out.println(a.getDescription().getNull());
        System.out.println("** Description");
        System.out.println(a.getDescription().hasValue());
        System.out.println("** Price");
        System.out.println(a.getPrice().hasValue());
        System.out.println("** Stock");
        System.out.println(a.getStock().hasValue());
        System.out.println("** World");
        System.out.println(a.getWorldStock().hasValue());


        // Read data from file created in nodejs
        System.out.println("================= From json file");
        try{
            InputStream sreamData = this.getClass().getClassLoader().getResourceAsStream("bytes.json");
            ObjectMapper objectMapper = new ObjectMapper();
            Data d = objectMapper.readValue(sreamData, Data.class);
            System.out.println(d);
            Stock.Laptop lap = Stock.Laptop.parseFrom(d.getData());
            System.out.println(lap);
            System.out.println("*** description");
            System.out.println(lap.getDescription().hasValue());
            System.out.println("*** price");
            System.out.println(lap.getPrice().hasValue());

        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }


    }
}