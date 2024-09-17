# JacocoCoverageAnalyzer

`JacocoCoverageAnalyzer` 是一个用于分析 JaCoCo 生成的 `.exec` 文件并输出指定类或方法的代码覆盖率的工具。该工具可以帮助你了解测试用例对代码的覆盖情况，从而优化测试用例。

## 功能

- 分析 JaCoCo 生成的 `.exec` 文件。
- 输出指定类的所有方法的覆盖率。
- 输出指定类的指定方法的覆盖率。

## 依赖

- Java 21
- Maven
- JaCoCo 0.8.8

## 构建项目

1. 克隆项目到本地：

   ```bash
   git clone https://github.com/yourusername/JacocoCoverageAnalyzer.git
   cd JacocoCoverageAnalyzer
   ```

2. 使用 Maven 构建项目：

   ```bash
   mvn clean install
   ```

   构建成功后，生成的 JAR 文件将位于 `target` 目录下，文件名为 `coverage-1.0-SNAPSHOT.jar`。

## 使用方法

### 输出指定类的所有方法的覆盖率

```bash
java -jar target/coverage-1.0-SNAPSHOT.jar \
  <execFilePath> \
  <classFilesPath> \
  <targetClassName>
```

#### 参数解释

- `<execFilePath>`：JaCoCo 生成的 `.exec` 文件路径。
- `<classFilesPath>`：包含目标类文件的目录路径。
- `<targetClassName>`：目标类的全限定名。

#### 示例

```bash
java -jar target/coverage-1.0-SNAPSHOT.jar \
  ~/fuzzing-repo/targets/java/simple-openai/target/jacoco.exec \
  ~/fuzzing-repo/targets/java/simple-openai/target/classes \
  io.github.sashirestela.openai.SimpleOpenAIAzure
```

### 输出指定类的指定方法的覆盖率

```bash
java -jar target/coverage-1.0-SNAPSHOT.jar \
  <execFilePath> \
  <classFilesPath> \
  <targetClassName> \
  <targetMethodName>
```

#### 参数解释

- `<execFilePath>`：JaCoCo 生成的 `.exec` 文件路径。
- `<classFilesPath>`：包含目标类文件的目录路径。
- `<targetClassName>`：目标类的全限定名。
- `<targetMethodName>`：目标方法的名称。

#### 示例

```bash
java -jar target/coverage-1.0-SNAPSHOT.jar \
  ~/fuzzing-repo/targets/java/simple-openai/target/jacoco.exec \
  ~/fuzzing-repo/targets/java/simple-openai/target/classes \
  io/github/sashirestela/openai/SimpleOpenAIAzure \
  yourMethodName
```

## 示例输出

### 输出指定类的所有方法的覆盖率

```bash
yourMethodName()V: 0.85
anotherMethodName(Ljava/lang/String;)I: 0.90
```

### 输出指定类的指定方法的覆盖率

```bash
覆盖率: 0.85
```

## 贡献

欢迎贡献代码、报告问题或提出建议。请提交 Pull Request 或 Issue 到 GitHub 仓库。

## 许可证

本项目采用 MIT 许可证。详细信息请参阅 [LICENSE](LICENSE) 文件。
```
