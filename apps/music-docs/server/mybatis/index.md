---
outline: deep
---

# Mybatis Dynamic SQL

> [!NOTE]
> 记录常用的[mybatis dynamic](https://mybatis.org/mybatis-dynamic-sql/docs/introduction.html) 用法

在使用MyBatis Dynamic SQL的时候，有两种方式写SQL。

- StatementProvider
- DSLCompleter

## StatementProvider

> [!TIP]
> 通过SqlBuilder来构建statement

```java
DeleteStatementProvider deleteProvider = deleteFrom(songList)
                .where(id, isEqualTo(id_))
                .build()
                .render(RenderingStrategies.MYBATIS3);
songListMapper.delete(deleteProvider);
```

## DSLCompleter

> [!TIP]
> 直接通过lambda表达式来构建SQL,这种方式会边间简单
> 缺点就是如果是查询，这是一种全字段查询

```java
songListMapper.delete(c -> c.where(id,isEqualTo(id_)));
```
