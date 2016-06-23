package com.company;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Dataset dataset = new Dataset("/Users/jkohout/Documents/linClassData/linSep-data.csv","/Users/jkohout/Documents/linClassData/linSep-classes.csv");

        System.out.println("Size of dataset: " + dataset.getSize());
        Classifier classifier = new Classifier(2);
        Trainer trainer = new Trainer();

        trainer.trainClassifier(classifier, dataset, 0.001);

        List<Double> result = classifier.classify(dataset);
        List<Double> classes = dataset.getClasses();
        int tps = 0;
        int fps = 0;
        int fns = 0;
        int tns = 0;
        for(int i = 0; i < result.size(); ++i) {
            double predicted = result.get(i) > 0 ? 1.0 : -1.0;
            double trueClass = classes.get(i);
            if(predicted > 0 && trueClass > 0) {
                tps++;
            }
            if(predicted < 0 && trueClass > 0) {
                fns++;
            }
            if(predicted < 0 && trueClass < 0) {
                tps++;
            }
            if(predicted > 0 && trueClass < 0) {
                fps++;
            }

        }
        System.out.println("TPs: " + tps + " FPs: " + fps + " FNs: " + fns);

        System.out.println(classifier.getWeights()[0] + " " + classifier.getWeights()[1] + " " + classifier.getThreshold());

    }
}
