
# 启动docker服务

```shell
# 带着控制台信息启动
docker compose up 

# 后台启动（静默）
docker compose up -d
```

# docker端口占用

```shell
net stop winnat
net start winnat

netstat -ano | findstr "8080"
taskkill /f /pid id的数字
```

# 服务

## Minio
[http://localhost:9001/](http://localhost:9001/)