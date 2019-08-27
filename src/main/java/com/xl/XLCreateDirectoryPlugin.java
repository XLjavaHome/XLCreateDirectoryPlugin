package com.xl;

import java.io.File;
import org.apache.maven.model.Build;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * Created with 徐立. 创建maven目录
 *
 * @author 徐立
 * @date 2019-08-25
 * @time 0:30
 * To change this template use File | Settings | File Templates.
 */
@Mojo(name = "createDirectoryPlugin", defaultPhase = LifecyclePhase.PACKAGE)
public class XLCreateDirectoryPlugin extends AbstractMojo {
    public static final String RESOURCES = "resources";
    /**
     * 默认参数，接受项目运行的参数
     */
    @Parameter(defaultValue = "${project}")
    private MavenProject project;
    
    public void execute() {
        Build build = project.getBuild();
        Log log = getLog();
        log.info("create directory");
        File sourceDirectory = new File(build.getSourceDirectory());
        File resource = getResourcesFile(sourceDirectory);
        createDirectory(log, sourceDirectory);
        createDirectory(log, resource);
        File testSourceDirectory = new File(build.getTestSourceDirectory());
        createDirectory(log, testSourceDirectory);
        File testResource = getResourcesFile(testSourceDirectory);
        createDirectory(log, testResource);
        log.info("create directory sucess");
    }
    
    /**
     * 获取资源目录
     *
     * @param file
     * @return
     */
    private File getResourcesFile(File file) {
        return new File(file.getParent(), RESOURCES);
    }
    
    /**
     * 创建目录
     *
     * @param log
     * @param file
     */
    private void createDirectory(Log log, File file) {
        if (!file.exists()) {
            file.mkdirs();
            log.info(file.getPath() + " 创建成功!");
        }
    }
}
