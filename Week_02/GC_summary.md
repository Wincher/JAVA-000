# GC test summary

## Serial GC
> 单核串行GC,会用STW来进行GC,小内存时候用,当运内较大时,不建议使用. 测试1G内存,wrk压测与其它GC性能相当,简单场景低内存场景更优.

## Parallel GC
> 并行GC,利用多核CPU,性能由cpu处理数决定, 并行GC吞吐量较大,JDK8默认是Parallel GC.

##CMS
> Young generation 使用ParNew算法.old generation采用标记清除算法,用于避免old长时间卡顿, 默认1/4的核心. 网关测试中,1G内存时的RPS还不如SerialGC.调优中要根据具体机器配置做决定.
>
>## G1
> 默认2048个分区,1M,标记整理复制算法.运行内存较大时,建议使用. 测试案例中,程序比较简单,内存设置过大, 并未触发oldGC,性能反而下降.