import axios from "axios"
import React, { useState } from "react"
import { Input,Button} from "reactstrap"
import "../../src/assets/css/Login.css"
import { useHistory } from "react-router"
import { useDispatch } from "react-redux"


const RegisterPage = ()=>{

    const hist = useHistory()
    const dispatcher = useDispatch()

    const [fullName,setFullName] = useState("") 
    const [username,setUsername] = useState("")
    const [password,setPassword] = useState("")


    const handleSubmit=(e)=>{
        e.preventDefault();
        console.log(username,password,fullName)
        axios.post("http://localhost:8080/v1/register",{
            username,
            password,
            fullName
        },{
            headers:{
                "Access-Control-Expose-Headers": "Authorization"
            }
        }).then(res=>{
            localStorage.setItem("token",res.data.token)
            localStorage.setItem("username",res.data.username)
            dispatcher({type:"LOGIN",payload:res.data.username})
            hist.push("/")
        })
    }

    return (
        <div className="card login-card register-height">
            <div className="login-header">
                <div>
                    <label>
                        Welcome!
                    </label>
                    <label>
                        Register in to continue
                    </label>
                </div>
                <img width="40px" height="40px" src={"http://skote-v-light.react.themesbrand.com/static/media/profile-img.43b59e59.png"} alt="logo"/>

            </div>
            <div>
                <form onSubmit={handleSubmit}>
                <div className="form-holder">
                        <label className="form-margin">
                            full name
                        </label>
                        <Input className="form-control" onChange={e=>setFullName(e.target.value)} type="text"/>
                        
                    </div>
                    <div className="form-holder">
                        <label className="form-margin">
                            username
                        </label>
                        <Input className="form-control" onChange={e=>setUsername(e.target.value)} type="text"/>
                        
                    </div>
                    <div className="form-holder">
                        <label className="form-margin">
                            password
                        </label>
                        <Input className="form-control" onChange={e=>setPassword(e.target.value)} type="password"/>
                        
                    </div>
                    <div className="center" >

                        <Button className="btn-primary">Register</Button>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default RegisterPage;