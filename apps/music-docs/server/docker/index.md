# 基础服务

> [!NOTE]
> 使用docker compose安装基础服务

```yaml
services:
  mysql:
    container_name: music_mysql
    image: mysql:8.0
    restart: unless-stopped
    volumes:
      - './docker_data/mysql:/var/lib/mysql'
    ports:
      - '3309:3306'
    environment:
      - MYSQL_ROOT_PASSWORD=Root.123456
      - MYSQL_DATABASE=pkmer_music
  minio:
    container_name: music_minio
    image: minio/minio:RELEASE.2024-12-18T13-15-44Z.fips
    restart: always
    volumes:
      - './docker_data/minio/data:/data' # 数据存储目录
      - './docker_data/minio/config:/root/.minio' # 配置目录
    ports:
      - '9000:9000' # MinIO API 端口
      - '9001:9001' # MinIO 控制台端口
    environment:
      - MINIO_ROOT_USER=root
      - MINIO_ROOT_PASSWORD=Root.123456
    command: server /data --console-address ":9001"
```
