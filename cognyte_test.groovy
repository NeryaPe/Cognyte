// The parameters directive provides a list of parameters which a user should provide when triggering 
// the Pipeline.
pipeline {
    agent any
    parameters {
        string(name: 'EMP_NAME', defaultValue: 'Mr Jenkins', description: 'Employee name: Who should I say hello to?')

        

        text(name: 'IP', defaultValue: '192.168.33.39', description: 'Enter some information about the VM name / IP')
       
        file(name: "FILE", description: 'Choose path to upload file1.zip from local system.') 
        //file(name: "FILE", file: "file1.zip", description: 'Choose path to upload file1.zip from local system.') 

       // text(name: 'FILE', defaultValue: '', description: 'file upload - the user should be able to upload ssh-public-key file for the new employee.')
            

        //booleanParam(name: 'TOGGLE', defaultValue: true, description: 'Toggle this value')

        //choice(name: 'CHOICE', choices: ['One', 'Two', 'Three'], description: 'Pick something')

        //password(name: 'PASSWORD', defaultValue: 'SECRET', description: 'Enter a password')
    }


    
    stages {
        //
        stage('Get properties file from user and send to slave workspace') {
                steps {
                      
                            script{
                                    // Get file using input step, will put it in build directory
                                    print "=================Please upload your property files ====================="
                                   // def inputFile = input message: 'Upload file', parameters: [file(name: 'global.properties')]
                                    // Read contents and write to workspace
                                    //writeFile(file: 'global.properties', text: inputFile.readToString())
                                    // Stash it for use in a different part of the pipeline
                                    //stash name: 'data', includes: 'global.properties'
                            }
                      
                }
            }
            //
        stage('Example') {
            steps {
                echo "Hello"

                echo "Employee name: ${params.EMP_NAME}"

                echo "file upload: ${params.FILE}"

                echo "VM name / IP: ${params.IP}"



            }
        }

   /*        stage ('Checkout 1') {
          steps {
            git 'https://github.com/lev-tmp/jenkins2-course-spring-petclinic.git'
          }
        }*/

        stage('Checkout') {
            steps {
                git 'https://github.com/NeryaPe/Cognyte.git'
            }
        }

        stage('Execute Ansible') {
            steps {
                //ansiblePlaybook become: true, colorized: true, credentialsId: 'lancert', disableHostKeyChecking: true, installation: 'ansible2', playbook: 'setup.yml'
                sh "sudo ansible-playbook -i ${params.IP}, setup.yml"
            }

        }

//https://tech.davidfield.co.uk/from-0-to-code-using-ansible-in-jenkins-pipelines/



    }
}