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