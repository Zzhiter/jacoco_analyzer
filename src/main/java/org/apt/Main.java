package org.apt;

import org.jacoco.core.analysis.Analyzer;
import org.jacoco.core.analysis.CoverageBuilder;
import org.jacoco.core.data.ExecutionDataReader;
import org.jacoco.core.data.ExecutionDataStore;
import org.jacoco.core.tools.ExecFileLoader;

import java.io.File;
import java.io.FileInputStream;

class JacocoCoverageAnalyzer {
    public static void main(String[] args) throws Exception {
        // 加载 exec 文件
        ExecFileLoader execFileLoader = new ExecFileLoader();
        execFileLoader.load(new FileInputStream("/home/zhangzhe/fuzzing-repo/targets/java/simple-openai/target/jacoco.exec"));

        // 创建 CoverageBuilder 来分析类文件
        CoverageBuilder coverageBuilder = new CoverageBuilder();
        Analyzer analyzer = new Analyzer(execFileLoader.getExecutionDataStore(), coverageBuilder);

        // 分析目标类文件
        analyzer.analyzeAll(new File("/home/zhangzhe/fuzzing-repo/targets/java/simple-openai/target/classes/io/github/sashirestela/openai/SimpleOpenAIAzure.class"));

        // 遍历所有类的覆盖率
        coverageBuilder.getClasses().forEach(classCoverage -> {
            if (classCoverage.getName().equals("io/github/sashirestela/openai/SimpleOpenAIAzure")) {
                classCoverage.getMethods().forEach(methodCoverage -> {
                    // if (methodCoverage.getName().equals("yourMethodName")) {
                    //     System.out.println("覆盖率: " + methodCoverage.getInstructionCounter().getCoveredRatio());
                    // }
                    System.out.println(methodCoverage.getName() + ": " + methodCoverage.getInstructionCounter().getCoveredRatio());
                });
            }
            // System.out.println(classCoverage.getName());
        });
    }
}