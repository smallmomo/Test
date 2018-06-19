
node("master") {
    checkout scm
    def workspace = pwd()
    def TOMCAT_HOME = "/var/tmp/tomcat/apache-tomcat-8.5.8"
    def MVN_HOME = tool 'Maven'
    def MVN_BIN = "${MVN_HOME}/bin/mvn"
    stage('InitDB') {
        echo "InitDB start..."
        return
    }
    stage('Build') {
        echo "InitDB start..."
        return
    }
    stage('Test') {
        echo "Test start..."
        sh """
            ${MVN_BIN} clean && ${MVN_BIN} test
        """
    }
    stage('Deploy') {
        echo "Deploy start..."
        // 直接修改/opt/tomcat1/bin/catalina.sh，指定三个环境变量
        // export CATALINA_BASE=/opt/tomcat1
        // export CATALINA_HOME=/opt/tomcat1
        // export CATALINA_PID=/opt/tomcat1/temp/tomcat.pid
        sh """
            ${MVN_BIN} package -Dmaven.test.skip=true
            ${TOMCAT_HOME}/bin/catalina.sh stop || true
            cp target/MyNoteJ4-0.0.1-SNAPSHOT.war ${TOMCAT_HOME}/webapps/
            ${TOMCAT_HOME}/bin/catalina.sh start
        """
    }
}

