
pipeline {
    agent any
    parameters {
        string(name: 'EMP_NAME', defaultValue: 'Mr Jenkins', description: 'Employee name: Who should I say hello to?')  

        text(name: 'IP', defaultValue: '192.168.33.39', description: 'Enter some information about the VM name / IP')
       
    }
    
    stages {

        stage('Get key file from user and send to slave workspace') {
                steps {
                      
                            script{
                                    // Get file using input step, will put it in build directory
                                    print "=================Please upload your property files ====================="
                                    def inputFile = input message: 'Upload file', parameters: [file(name: 'global.txt')]

                                    // Read contents and write to workspace
                                    writeFile(file: 'global.txt', text: inputFile.readToString())

                                    // Stash it for use in a different part of the pipeline
                                    stash name: 'data', includes: 'global.txt'

                                    echo fileExists('global.txt').toString()

                                     // file in  '$workspace/global.txt'
  
                            }
                      
                }
            }
            
        stage('Print parameters & Verify') {
            steps {

                echo "Employee name: ${params.EMP_NAME}"

                echo "VM name / IP: ${params.IP}"

                // Verify the user input: 
                script {
                    if ( null == params.EMP_NAME  ){ // || String == params.EMP_NAME.getClass()
                        currentBuild.result = 'ABORTED'
                        error('not valid username string')
                    }
                }

            }
        }

        stage('Checkout') {
            steps {
                git 'https://github.com/NeryaPe/Cognyte.git'
            }
        }

        stage('Execute Ansible') {
            steps {
                // Execute ansible with plugin
                //ansiblePlaybook become: true, colorized: true, credentialsId: 'lancert', disableHostKeyChecking: true, installation: 'ansible2', playbook: 'setup.yml'
                // Execute ansible with out plugin
                sh "sudo ansible-playbook -i ${params.IP}, setup.yml"
            }

        }


    }
}
