package com.company;


import java.util.ArrayList;
import java.util.List;

public class Trainer {

    public void trainClassifier(Classifier classifier, Dataset dataset, double epsilon) {

        double delta = Double.MAX_VALUE;
        while(delta > epsilon) {
            double[] wGrad = weightGradient(classifier, dataset);
            double thrGrad = thrGradient(classifier, dataset);

            double[] originalWeights = classifier.getWeights();
            double originalThr = classifier.getThreshold();
            classifier.updateWeights(new double[]{-0.01*wGrad[0], -0.01*wGrad[1]}, -0.01*thrGrad);

            double[] newWeights = classifier.getWeights();
            double newThr = classifier.getThreshold();

            delta = Math.abs(originalWeights[0]-newWeights[0]) + Math.abs(originalWeights[1]-newWeights[1]) + Math.abs(originalThr-newThr);
        }

    }

    private double[] weightGradient(Classifier classifier, Dataset dataset) {
        double count = dataset.getSize();
        List<Double> classifications = classifier.classify(dataset);
        List<Double> classes = dataset.getClasses();

        double wGrad1 = 0.0;
        for(int i = 0; i < classifications.size(); ++i) {
            wGrad1 += (classifications.get(i)-classes.get(i))*dataset.getData().get(i).x;
            System.out.println("--- wGrad1 " + wGrad1);
        }
        System.out.println("wGard1 is " + wGrad1);
        wGrad1 = 2*wGrad1/count;

        double wGrad2 = 0.0;
        for(int i = 0; i < classifications.size(); ++i) {
            wGrad2 += (classifications.get(i)-classes.get(i))*dataset.getData().get(i).y;
        }
        System.out.println("wGard2 is " + wGrad1);
        wGrad2 = 2*wGrad2/count;

        return new double[] {wGrad1, wGrad2};
    }

    private double thrGradient(Classifier classifier, Dataset dataset) {
        double count = dataset.getSize();
        List<Double> classifications = classifier.classify(dataset);
        List<Double> classes = dataset.getClasses();
        double result = 0.0;
        for(int i = 0; i < classifications.size(); ++i) {
            result += classifications.get(i)-classes.get(i);
        }
        return 2*result/count;
    }

}
