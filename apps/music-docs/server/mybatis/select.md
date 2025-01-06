---
outline: deep
---

## 选择一条数据

```java
Optional<SongList> optionalSongList = songListMapper.selectOne(
                c -> c.where(id,isEqualTo(songListId.value()))
            );
```

## 动态where构建条件

### in 条件

> [!IMPORTANT]
> 注意[Mybatis Dynamic SQL官网描述空where渲染报错的情况](https://mybatis.org/mybatis-dynamic-sql/docs/configuration.html#global-configuration-properties) > `If a where clause is specified, but fails to render, then the library will throw a NonRenderingWhereClauseException by default` ，所以需要配置`setNonRenderingWhereClauseAllowed(true)`，允许空where渲染

这里如果我们的`ids`为一个空的List，那么最终这里是一个空`where()`

```java:line-numbers{8,9}
        WhereApplier whereApplier = where().toWhereApplier();
        if(ids != null && !ids.isEmpty()){
            whereApplier = where(id, isIn(ids)).toWhereApplier();
        }

        SelectStatementProvider selectProvider = select(banner.allColumns())
                .from(banner)
                .applyWhere(whereApplier)
                .configureStatement(c -> c.setNonRenderingWhereClauseAllowed(true))
                .build()
                .render(RenderingStrategies.MYBATIS3);
```
