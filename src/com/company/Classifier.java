package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Classifier {

    private double[] weights;
    private double threshold;

    public Classifier(int dim) {
        Random random = new Random(1234);
        weights = new double[dim];
        for(int i = 0; i < dim; ++i) {
            weights[i] = 0.0;//random.nextDouble();
        }
        threshold = random.nextDouble();
    }

    public List<Double> classify(Dataset dataset) {
        List<Double> results = new ArrayList<>();
        for(Dataset.Point point : dataset.getData()) {
            results.add(classifyPoint(point));
        }
        return results;
    }

    public void updateWeights(double[] weightUpdates, double thrUpdate) {
        for(int i = 0; i < weights.length; ++i) {
            weights[i] += weightUpdates[i];
        }
        threshold += thrUpdate;
    }

    public double[] getWeights() {
        return weights;
    }

    public double getThreshold() {
        return threshold;
    }

    private double classifyPoint(Dataset.Point point) {
        return point.x*weights[0] + point.y*weights[1] + threshold;
    }

}
