package com.company;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dataset {

    private List<Point> data = new ArrayList<>();
    private List<Double> classes = new ArrayList<>();
    private int size;

    public Dataset(String dataPath, String classesPath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(dataPath)));
            String line;
            while((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                data.add(new Point(Double.parseDouble(tokens[0]), Double.parseDouble(tokens[1])));
            }
            br.close();

            br = new BufferedReader(new FileReader(new File(classesPath)));
            while((line = br.readLine()) != null) {
                classes.add(Double.parseDouble(line));
            }
            br.close();
        }
        catch (Exception e) {
            System.out.println("Could not load dataset: " + e.getMessage());
            e.printStackTrace();
        }

        size = data.size();
    }

    public List<Point> getData() {
        return data;
    }

    public List<Double> getClasses() {
        return classes;
    }

    public int getSize() {
        return size;
    }

    class Point {

        double x;
        double y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

    }
}
