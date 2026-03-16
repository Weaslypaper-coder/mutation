# mutation-testing-system（变异测试实验平台）

基于 Spring Boot + Thymeleaf 的教学演示系统，所有数据保存在 Java 内存中的 `List` 集合中，不依赖数据库，适合作为变异测试相关课程与论文原型展示。

## 一、技术栈

- **后端**：Spring Boot 3、Spring MVC
- **模板引擎**：Thymeleaf
- **前端**：HTML + CSS + JavaScript
- **构建工具**：Maven
- **数据存储**：Java 内存 `List`（无数据库）

## 二、主要业务实体

- `User`：用户（内置账号：`admin` / 密码：`123456`）
- `Program`：被测程序（例如 `TRANS.java`）
- `MutantBranch`：变异分支（自动生成 `Mutant_1` ~ `Mutant_20`）
- `ExperimentParameter`：实验参数（种群数量、编码方式、交叉/变异概率、样本数量）
- `ModelResult`：模型训练结果（模型名称、MSE、U 统计量、IA 统计量）

## 三、主要 Service（全部基于内存 List）

- `UserService`：管理用户，初始化默认管理员账号。
- `ProgramService`：保存和查询测试程序。
- `ExperimentService`：记录当前测试程序，管理 `List<MutantBranch>` 并生成 20 个变异分支。
- `ParameterService`：保存当前一次试验的参数配置。
- `ModelService`：保存当前模型训练结果列表。

## 四、主要 Controller 与页面映射

- `LoginController`
  - `GET /`、`GET /login`：登录页 `login.html`
  - `POST /login`：登录校验（成功跳转 `/home`，失败返回登录页并提示错误）

- `HomeController`
  - `GET /home`：主界面 `home.html`
  - `GET /algorithm`：算法简介 `algorithm.html`
  - `GET /experiment`：实验选择 `experiment-select.html`

- `ExperimentController`
  - `POST /experiment/start`：从实验选择页面提交，跳转到程序测试页 `program-test.html`
  - `GET /experiment/program-test`：程序测试主界面（左侧导航 + 右侧内容）
  - `POST /experiment/import-program`：导入程序（示例：`TRANS.java`）
  - `POST /experiment/generate-mutants`：生成 20 个变异分支
  - `GET /experiment/correlation-heatmap`：相关度热力图页面 `correlation-heatmap.html`
  - `GET /experiment/correlation-graph`：相关图页面 `correlation-graph.html`
  - `GET /experiment/data-generation`：测试数据生成页面 `data-generation.html`

- `ParameterController`
  - `GET /parameter/setting`：实验参数设置页 `parameter-setting.html`
  - `POST /parameter/save`：保存实验参数（返回程序测试页）

- `ModelController`
  - `GET /model/training`：模型训练页 `model-training.html`
  - `POST /model/train`：根据选择的 CNN / DNN / RNN 生成一条模拟训练结果

## 五、页面与图片说明

**所有页面均使用 Thymeleaf 模板，并包含统一的导航栏、标题、按钮与图片展示，整体为居中、简洁、学术风格。**

图片全部放在 `src/main/resources/static/images/` 下，**文件名为中文**，前端引用路径统一为：

```text
/images/图片名.png
```

主要示例：

- 登录背景：`/images/登录界面.png`
- 算法简介背景：`/images/算法简介界面.png`
- 程序导入示意：`/images/被测程序导入成功.png`
- 相关度热力图：`/images/计算变异分支相关度.png`
- 相关图结构：`/images/构建变异分支相关图.png`
- 模型评估：`/images/MSE结果.png`、`/images/U统计量结果.png`、`/images/IA统计结果.png`
- 测试数据效率：`/images/测试数据生成效率.png`

## 六、运行方式

1. 确保本机安装了 JDK 17+ 与 Maven。
2. 在项目根目录（包含 `pom.xml` 的目录）执行：

```bash
mvn spring-boot:run
```

3. 浏览器访问：

- 登录页：`http://localhost:8080/login` 或 `http://localhost:8080/`
- 使用账号 `admin`、密码 `123456` 登录。

## 七、典型操作流程（实验演示）

1. **登录系统**
   - 打开 `/login`，输入 `admin / 123456`。
2. **主界面**
   - 在 `/home` 点击“算法简介”或“实验演示”。
3. **实验选择**
   - 在 `experiment-select.html` 中选择测试系统（P1/P2/P3）、测试项目（TRANS/SORT/SEARCH）、编程语言（Java/Python/C），点击“开始测试”。
4. **程序测试页**
   - 在左侧导航中先点击“导入程序”（自动导入 `TRANS.java` 并展示导入成功图片）。
   - 再点击“生成变异分支”，系统会在内存中生成 20 个 `Mutant_x` 分支并在页面表格中展示。
5. **实验参数设置**
   - 点击“实验参数设置”，配置种群数量、编码方式、交叉/变异概率、样本数量，保存后返回程序测试页。
6. **相关度分析与相关图**
   - 通过左侧导航依次访问“计算相关度”“构建相关图”，查看对应热力图与图结构图片。
7. **模型训练**
   - 进入“训练预测模型”，选择 CNN / DNN / RNN，点击“开始训练”，查看模拟的 MSE/U/IA 结果与对应图像。
8. **生成测试数据**
   - 进入“生成测试数据”，点击“生成测试数据”按钮，页面会显示生成完成提示及效率图。

本系统主要面向教学与论文配套展示，因此所有逻辑均简化为内存级原型实现，便于理解变异分支相关度、参数设置与模型训练之间的整体流程。
