node{
    stage("Update Jenkins"){
        properties([parameters([string(defaultValue: '18.222.222.138', description: 'please provide ip', name: 'Environment', trim: true)])])
        sh "echo Parameter added"
    }
    stage("Install git"){
        sh "ssh ec2-user${Environment} sudo yum install git -y"
    }
    stage("Pull Repo"){
        sh "ssh ec2-user${Environment} git clone https://github.com/miguelgrinberg/flask-examples.git"
        

    }
    stage("Install Requirement"){
        //sh "virtualenv /tmp/venv"
        //sh ". /tmp/venv/bin/activate"
        sh "echo Hello"
    }
    stage("Pip install"){
        sh "ssh ec2-user${Environment} pip install -r ~/requirements.txt"
    }
    stage("Run App"){
        sh "ssh ec2-user${Environment} python ~/flask-examples/01-hello-world/hello.py"
        
    }
} 