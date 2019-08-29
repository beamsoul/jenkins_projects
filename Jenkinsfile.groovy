node{
    stage("Update jenkins"){
        properties([parameters([string(defaultValue: '18.222.222.138', description: 'please provide IP', name: 'ENVIR', trim: true)])])
        sh "echo Parameter added"
    }
    stage("Install git"){
        sh "ssh  ec2-user@${ENVIR} sudo yum install git python-pip -y"
    }
    stage("Remove repo"){
        sh "ssh  ec2-user@${ENVIR} sudo  rm -rf /home/ec2-user/flask-examples"
    }
    stage("Pull Repo"){
        sh "ssh  ec2-user@${ENVIR} git clone https://github.com/beamsoul/flask-examples.git"
    }
    stage("Install Requirements"){
        //sh "virtualenv /tmp/venv"
        //sh ". /tmp/venv/bin/activate"
        sh "echo Hello"
    }
    stage("Pip Install"){
        sh "ssh  ec2-user@${ENVIR} sudo pip install -r /home/ec2-user/flask-examples/requirements.txt"
    }
    stage("Run App"){
        sh "ssh  ec2-user@${ENVIR}  python /home/ec2-user/flask-examples/01-hello-world/hello.py"
    }
}