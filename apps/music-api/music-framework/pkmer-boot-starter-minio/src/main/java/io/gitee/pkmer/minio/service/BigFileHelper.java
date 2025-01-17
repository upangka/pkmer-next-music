
package io.gitee.pkmer.minio.service;

import io.gitee.pkmer.minio.props.PkmerMinioProps;
import io.gitee.pkmer.minio.utils.SizeUnitUtil;



public class BigFileHelper {

    private final long CHUNK_SIZE;

    public BigFileHelper(PkmerMinioProps props) {
        String defaultChunkSize = props.getDefaultChunkSize();
        this.CHUNK_SIZE = SizeUnitUtil.convertToBytes(defaultChunkSize);
    }

    /**
     * 计算文件分片的数量
     *
     * @param fileSize 文件的大小，以字节为单位
     * @return 分片的数量
     */
    public int computeChunks(long fileSize) {
        return (int) Math.ceil((double) fileSize / CHUNK_SIZE);
    }


    /**
     * 获取分片大小
     */
    public long getChunkSize(){
        return CHUNK_SIZE;
    }
}


