package com.kxkg.youehu.gaopeic.http.api;

/**
 * Created by wangyuyuan on 2017/7/28.
 *
 * 缓存API接口
 *
 * @LifeCache设置缓存过期时间. 如果没有设置@LifeCache , 数据将被永久缓存理除非你使用了 EvictProvider, EvictDynamicKey or EvictDynamicKeyGroup .
 * EvictProvider可以明确地清理清理所有缓存数据.
 * EvictDynamicKey可以明确地清理指定的数据 DynamicKey.
 * EvictDynamicKeyGroup 允许明确地清理一组特定的数据. DynamicKeyGroup.
 * DynamicKey驱逐与一个特定的键使用EvictDynamicKey相关的数据。比如分页，排序或筛选要求
 * DynamicKeyGroup。驱逐一组与key关联的数据，使用EvictDynamicKeyGroup。比如分页，排序或筛选要求
 */

public interface CacheProviders {

//    //资讯tab列数据
//    @LifeCache(duration = 1, timeUnit = TimeUnit.HOURS)
//    Observable<Reply<BaseResponse<List<HomeInfoListBean>>>> getHomeInfoTabData(Observable<BaseResponse<List<HomeInfoListBean>>> oRepos, DynamicKey userName, EvictDynamicKey evictDynamicKey);

}
