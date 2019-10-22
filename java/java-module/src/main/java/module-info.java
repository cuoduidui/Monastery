module java.module {
    requires java.base;//導入包
    requires transitive java.logging;
    exports com.cdd.module;//传递依赖 requires transitive
}