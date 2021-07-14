import React,{useState,useEffect} from 'react';
import "./../assets/css/Table.css"
import { Container, Row, Col,   UncontrolledDropdown, DropdownToggle, DropdownMenu, DropdownItem } from "reactstrap";
import more from  "./../assets/images/more.png"
import axios from 'axios';
import AddConfigurationModal from "./AddConfigurationModal"
import CompileModal from "./CompileModal"
import { useHistory } from 'react-router-dom';
import { useDispatch } from 'react-redux';


const Configurations =({currentTab,tabIndex})=>{

    const hist = useHistory()

    const dispatcher = useDispatch()

    const [data,setData] = useState([])
    const [addTemplateData,setAddTemplateData] = useState({isOpen:false,list:[]})
    const [compileModalParams,setCompileModalParams] = useState({isOpen:false,data:null})

    const toggleCompileModal=(value)=>{
        if (compileModalParams.isOpen){
            setCompileModalParams({isOpen:false,data:null})
        }else{
            setCompileModalParams({isOpen:true,data:value})
        }
    }
    const toggleModal=()=>{
        if (!addTemplateData.isOpen){

            axios.get("http://localhost:8080/v1/template",{
                headers:{
                    "Authorization":"Bearer "+localStorage.getItem("token").trim()
                }
            }).then(res=>{
                var curConf= addTemplateData;
                curConf.isOpen = true;
                curConf.list = res.data;
                curConf.list.map(val=> ({"label":val.keyword,"value":val.id}))
                setAddTemplateData({...curConf});
            })
        }else{
            var curConf= addTemplateData;
            curConf.isOpen = false;
            curConf.list = [];
            setAddTemplateData({...curConf});
        }
    }

    const fetchData=()=>{
        axios.get("http://localhost:8080/v1/filled-template",{ 
           headers:{
               'Authorization' :`Bearer ${localStorage.getItem("token")}`,
            }
        }
        ).then(res=>{
            setData(res.data)
        }).catch(error=>{
            if (error.response && error.response.status===403){
                hist.push("/login")
                dispatcher({type:"LOGOUT",payload:null})
            }
        })
    }

    const removeItem =(item)=>{
        axios.delete(`http://localhost:8080/v1/filled-template/${item.id}`,{
            headers:{
                "Authorization":"Bearer "+localStorage.getItem("token").trim()
            }
        }).then(res=>{
            fetchData()
        })
    }

    const compileItem = (item) =>{
        axios.get(`http://localhost:8080/v1/compile/${item.id}`,{
            headers:{
                "Authorization":"Bearer "+localStorage.getItem("token").trim()
            }
        }).then(res=>{
            toggleCompileModal(res.data)
        })
    }

    const sendEmail = (item) =>{
        axios.get(`http://localhost:8080/v1/compile/send-email/${item.id}`,{
            headers:{
                "Authorization":"Bearer "+localStorage.getItem("token").trim()
            }
        }).then(res=>{
            
        })
    }

    //@ts-ignore
    useEffect(()=>{
        if (currentTab===tabIndex){
            fetchData()   
        }
    },[currentTab,tabIndex])

    return (
        
        <React.Fragment>
        <div className="page-content">
            <Container fluid>
                <Row>
                    <Col lg="12">
                        <div className="full-width">
                            <div>
                                <div className="d-f j-c-f-e">
                                    <UncontrolledDropdown>
                                        <DropdownToggle href="#" className="card-drop" tag="i">
                                            <img width="20px" alt="more" src={more}/>
                                        </DropdownToggle>
                                        <DropdownMenu right>
                                            <DropdownItem onClick={toggleModal} href="#">Add Template Configuration</DropdownItem>
                                        </DropdownMenu>
                                    </UncontrolledDropdown>
                                </div>
                                <table className=" table-responsive project-list-table table-nowrap table-centered table-borderless full-width">
                                    <thead className="table-body">
                                        <tr>
                                            <th scope="col" >Mail</th>
                                            <th scope="col">Template</th>
                                            <th scope="col">Template Type</th>
                                            <th scope="col">Create Date</th>
                                            <th scope="col">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody className="table-body">
                                    {data.map((value,key)=>(
                                        <tr>
                                        <td>
                                            <p className="mb-0">{value.email}</p>
                                        </td>
                                        <td>
                                            <p className="mb-0 small-p" >{value.keyword}</p>
                                        </td>
                                        <td><span className="badge badge-primary">{value.templateType}</span></td>
                                        <td>
                                            {value.createTime}
                                        </td>
                                        <td>
                                            <UncontrolledDropdown>
                                                <DropdownToggle href="#" className="card-drop" tag="i">
                                                    <img width="20px" src={more} alt="more"/>
                                                </DropdownToggle>
                                                <DropdownMenu right>
                                                    <DropdownItem onClick={()=> compileItem(value)} href="#">Compile</DropdownItem>
                                                    <DropdownItem onClick={()=> removeItem(value)} href="#">Remove</DropdownItem>
                                                    <DropdownItem onClick={()=> sendEmail(value)} href="#">Send</DropdownItem>
                                                </DropdownMenu>
                                            </UncontrolledDropdown>
                                        </td>
                                    </tr>
                                    ))}
                                        
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </Col>
                </Row>

                <AddConfigurationModal
                    params={addTemplateData}
                    fetchData={fetchData}
                    handleVisible={toggleModal}
                />
                <CompileModal
                    handleVisible={toggleCompileModal}
                    params={compileModalParams}
                />
            </Container>
        </div>
    </React.Fragment>
    )
}

export default Configurations;