package org.apt;

import org.jacoco.core.analysis.Analyzer;
import org.jacoco.core.analysis.CoverageBuilder;
import org.jacoco.core.data.ExecutionDataReader;
import org.jacoco.core.data.ExecutionDataStore;
import org.jacoco.core.tools.ExecFileLoader;

import java.io.File;
import java.io.FileInputStream;

public class JacocoCoverageAnalyzer {
    public static void main(String[] args) throws Exception {
        if (args.length < 3) {
            System.err.println("Usage: java -jar coverage-1.0-SNAPSHOT.jar <execFilePath> <classFilesPath> <targetClassName> [targetMethodName]");
            System.exit(1);
        }

        String execFilePath = args[0];
        String classFilesPath = args[1];
        String targetClassName = args[2];
        String targetMethodName = args.length > 3 ? args[3] : null;

        // 加载 exec 文件
        ExecFileLoader execFileLoader = new ExecFileLoader();
        execFileLoader.load(new FileInputStream(execFilePath));

        // 创建 CoverageBuilder 来分析类文件
        CoverageBuilder coverageBuilder = new CoverageBuilder();
        Analyzer analyzer = new Analyzer(execFileLoader.getExecutionDataStore(), coverageBuilder);

        // 分析目标类文件
        analyzer.analyzeAll(new File(classFilesPath));

        // 遍历所有类的覆盖率
        coverageBuilder.getClasses().forEach(classCoverage -> {
            System.out.println("Class name: " + classCoverage.getName());
            if (classCoverage.getName().equals(targetClassName)) {
                if (targetMethodName != null) {
                    // 输出指定方法的覆盖率
                    classCoverage.getMethods().forEach(methodCoverage -> {
                        if (methodCoverage.getName().equals(targetMethodName)) {
                            System.out.println("Instruction Coverage: " + methodCoverage.getInstructionCounter().getCoveredRatio());
                            System.out.println("Branch Coverage: " + methodCoverage.getBranchCounter().getCoveredRatio());
                            System.out.println("Line Coverage: " + methodCoverage.getLineCounter().getCoveredRatio());
                        }
                    });
                } else {
                    // 输出所有方法的覆盖率
                    classCoverage.getMethods().forEach(methodCoverage -> {
                        System.out.println("Instruction Coverage: " + methodCoverage.getInstructionCounter().getCoveredRatio());
                        System.out.println("Branch Coverage: " + methodCoverage.getBranchCounter().getCoveredRatio());
                        System.out.println("Line Coverage: " + methodCoverage.getLineCounter().getCoveredRatio());                    });
                }
            }
        });
    }
}