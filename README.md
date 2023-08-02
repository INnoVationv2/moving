    <plugin>
                <groupId>io.github.fvarrui</groupId>
                <artifactId>javapackager</artifactId>
                <version>1.6.7</version>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>package</goal>
                        </goals>
                        <configuration>
                            <!-- 启动类 -->
                            <mainClass>com.amt.hardwareTest.MainApplication</mainClass>
                            <!-- 绑定自定义JRE路径-->
                            <bundleJre>true</bundleJre>
                            <generateInstaller>true</generateInstaller>
                            <administratorRequired>false</administratorRequired>
                            <!-- 操作系统-->
                            <platform>windows</platform>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
