import axios from "axios"
import React, { useState } from "react"
import {  Input,Button} from "reactstrap"
import { useHistory } from "react-router"
import "../../src/assets/css/Login.css"
import { useDispatch } from "react-redux"


const LoginPage = ()=>{
    const hist = useHistory()
    const dispatcher = useDispatch()

    const [username,setUsername] = useState("")
    const [password,setPassword] = useState("")

    const handleSubmit=(e)=>{
        e.preventDefault();
        axios.post("http://localhost:8080/v1/login",{
            username,
            password
        },{
            headers:{
                "Access-Control-Expose-Headers": "Authorization"
            }
        }).then(res=>{
            localStorage.setItem("token",res.data.token+"1")
            localStorage.setItem("username",res.data.username)
            dispatcher({type:"LOGIN",payload:res.data.username})
            hist.push("/")
        })
    }

    return (
        <div className="card login-card" >
            <div className="login-header">
                <div>
                    <label>
                        Welcome!
                    </label>
                    <label>
                        Sign in to continue
                    </label>
                </div>
                <img width="40px" height="40px" src={"http://skote-v-light.react.themesbrand.com/static/media/profile-img.43b59e59.png"} alt="logo"/>

            </div>
            <div>
                <form onSubmit={handleSubmit}>
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
                    <div className="center margin-top">
                        <Button className="btn-primary">Log in</Button>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default LoginPage;