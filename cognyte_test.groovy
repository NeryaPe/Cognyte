// The parameters directive provides a list of parameters which a user should provide when triggering 
// the Pipeline.
pipeline {
    agent any
    parameters {
        string(name: 'EMP_NAME', defaultValue: 'Mr Jenkins', description: 'Employee name: Who should I say hello to?')

        text(name: 'FILE', defaultValue: '', description: 'file upload - the user should be able to upload ssh-public-key file for the new employee.')

        text(name: 'IP', defaultValue: '', description: 'Enter some information about the VM name / IP')

        //booleanParam(name: 'TOGGLE', defaultValue: true, description: 'Toggle this value')

        //choice(name: 'CHOICE', choices: ['One', 'Two', 'Three'], description: 'Pick something')

        //password(name: 'PASSWORD', defaultValue: 'SECRET', description: 'Enter a password')
    }
    stages {
        stage('Example') {
            steps {
                echo "Hello"
                
                echo "Employee name: ${params.EMP_NAME}"

                echo "file upload: ${params.FILE}"

                echo "VM name / IP: ${params.IP}"


            }
        }
    }
}