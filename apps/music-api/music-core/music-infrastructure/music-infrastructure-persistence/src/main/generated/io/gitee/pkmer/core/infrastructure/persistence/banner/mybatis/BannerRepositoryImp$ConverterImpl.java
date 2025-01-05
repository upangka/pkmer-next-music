package io.gitee.pkmer.core.infrastructure.persistence.banner.mybatis;

import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.music.domain.banner.BannerEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-05T17:32:26+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
class BannerRepositoryImp$ConverterImpl implements BannerRepositoryImp.Converter {

    @Override
    public BannerEntity toEntity(Banner r) {
        if ( r == null ) {
            return null;
        }

        Long id = null;
        String pic = null;

        id = r.getId();
        pic = r.getPic();

        BannerEntity bannerEntity = new BannerEntity( id, pic );

        return bannerEntity;
    }

    @Override
    public List<BannerEntity> toEntities(List<Banner> list) {
        if ( list == null ) {
            return null;
        }

        List<BannerEntity> list1 = new ArrayList<BannerEntity>( list.size() );
        for ( Banner banner : list ) {
            list1.add( toEntity( banner ) );
        }

        return list1;
    }

    @Override
    public Banner toRecord(BannerEntity e) {
        if ( e == null ) {
            return null;
        }

        Banner banner = new Banner();

        banner.setId( e.getId() );
        banner.setPic( e.getPic() );

        return banner;
    }

    @Override
    public List<Banner> toRecords(List<BannerEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<Banner> list1 = new ArrayList<Banner>( list.size() );
        for ( BannerEntity bannerEntity : list ) {
            list1.add( toRecord( bannerEntity ) );
        }

        return list1;
    }

    @Override
    public PageResponse<Banner> convertToRecordPage(PageResponse<BannerEntity> pageResponse) {
        if ( pageResponse == null ) {
            return null;
        }

        PageResponse<Banner> pageResponse1 = new PageResponse<Banner>();

        pageResponse1.setTotal( pageResponse.getTotal() );
        pageResponse1.setTotalPages( pageResponse.getTotalPages() );
        pageResponse1.setCurrentPageNo( pageResponse.getCurrentPageNo() );
        pageResponse1.setList( toRecords( pageResponse.getList() ) );

        return pageResponse1;
    }

    @Override
    public PageResponse<BannerEntity> convertToEntityPage(PageResponse<Banner> pageResponse) {
        if ( pageResponse == null ) {
            return null;
        }

        PageResponse<BannerEntity> pageResponse1 = new PageResponse<BannerEntity>();

        pageResponse1.setTotal( pageResponse.getTotal() );
        pageResponse1.setTotalPages( pageResponse.getTotalPages() );
        pageResponse1.setCurrentPageNo( pageResponse.getCurrentPageNo() );
        pageResponse1.setList( toEntities( pageResponse.getList() ) );

        return pageResponse1;
    }
}
