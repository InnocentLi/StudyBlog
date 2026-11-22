## Docker 开发环境

先安装并启动 Docker Desktop，在项目根目录执行：

```powershell
docker compose up -d --build
```

该命令构建并启动开发容器。项目目录会挂载到容器的 `/workspace`，修改代码后无需重新构建镜像。
后续只需 `docker compose up -d` 启动已有容器；修改 Dockerfile 后再使用 `--build`。

在项目根目录运行以下命令，或将示例路径替换为自己的文件路径：

**Java**

```powershell
docker compose exec dev sh -c 'mkdir -p /tmp/studyblog && javac -encoding UTF-8 -d /tmp/studyblog BackEnd/Java/JavaBasic/02Encapsulation.java && java -Dfile.encoding=UTF-8 -cp /tmp/studyblog BackEnd.Java.JavaBasic.Student'
```

预期输出 `20`。运行继承示例：

```powershell
docker compose exec dev sh -c 'mkdir -p /tmp/studyblog && javac -encoding UTF-8 -d /tmp/studyblog BackEnd/Java/JavaBasic/02Inheritance.java && java -Dfile.encoding=UTF-8 -cp /tmp/studyblog BackEnd.Java.JavaBasic.InheritanceDemo'
```

运行引用拷贝、浅拷贝和深拷贝示例：

```powershell
docker compose exec dev sh -c 'mkdir -p /tmp/studyblog && javac -encoding UTF-8 -d /tmp/studyblog BackEnd/Java/JavaBasic/02copy.java && java -Dfile.encoding=UTF-8 -cp /tmp/studyblog BackEnd.Java.JavaBasic.CopyDemo'
```

运行其他 Java 示例时，请替换源文件路径和主类名；有 `package` 声明时使用包含包名的主类名。

**Python 和 Node.js**

```powershell
docker compose exec dev python3 path/to/example.py
docker compose exec dev node path/to/example.js
```

**C 和 C++**

```powershell
docker compose exec dev sh -c 'gcc path/to/example.c -o /tmp/studyblog-c && /tmp/studyblog-c'
docker compose exec dev sh -c 'g++ path/to/example.cpp -o /tmp/studyblog-cpp && /tmp/studyblog-cpp'
```

停止开发容器：

```powershell
docker compose stop
```

运行环境默认使用 JDK 17；以上命令中的容器路径使用 `/`，即使宿主机是 Windows 也一样。

## Docker 本地开发环境

仓库根目录提供了包含 C++、Java、Node.js 和 Python 工具链的开发容器。需要先安装并启动 Docker Desktop。

```bash
docker compose up -d --build
docker compose exec dev bash
```

进入容器后，当前目录是挂载的仓库根目录，文件修改会保存在本机项目中。可以按需运行：

```bash
# C++
g++ -std=c++20 -O2 Others/text.cpp -o /tmp/text && printf '0\n' | /tmp/text

# Java：先进入包含 Main.java 的目录
javac Main.java && java Main

# Node.js
node path/to/script.js

# Python
python3 path/to/script.py
```

容器还预装了 CMake、GDB、Maven 和 Git。退出容器 shell 后，可在本机停止容器：

```bash
docker compose down
```