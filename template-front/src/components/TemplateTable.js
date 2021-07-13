import React, { useEffect, useState } from 'react';
import "./../assets/css/Table.css"
import { Container, Row, Col,   UncontrolledDropdown, DropdownToggle, DropdownMenu, DropdownItem ,Input} from "reactstrap";
import more from  "./../assets/images/more.png"
import AddTemplateModal from "./AddTemplateModal"
import axios from 'axios';


const TemplateTable=({currentTab,tabIndex})=>{


    const [searchKeyword,setSearchKeyword] = useState("")
    const [addTemplateData,setAddTemplateData] = useState({
            isOpen:false,
            isBill:false,
            editableObj:null  
    })
    const [data,setData] = useState([])

    const toggleModal=(isBill)=>{
        var currParams=addTemplateData;
        currParams={
            isOpen:!currParams.isOpen,
            isBill: isBill,
            editableObj:null
        }
        setAddTemplateData({...currParams})
    }

    const editItem=(item)=>{
        var currParams=addTemplateData;
        currParams={
            isOpen:!currParams.isOpen,
            isBill: item.templateTypeEnum,
            editableObj:item
        }
        setAddTemplateData({...currParams})
    }

    const fetchData = ()=>{
        console.log(localStorage.getItem("token").trim())
        axios.get("http://localhost:8080/v1/template",{
            headers:{
                "Authorization":"Bearer "+localStorage.getItem("token").trim()
            }
        }).then(res=>{
            setData(res.data);
        })
    }

    const removeItem =(item)=>{
        axios.delete(`http://localhost:8080/v1/template/${item.id}`,{
            headers:{
                "Authorization":"Bearer "+localStorage.getItem("token").trim()
            }
        }).then(res=>{
            fetchData()
        })
    }
    
    useEffect(()=>{
        if (currentTab===tabIndex){
            axios.get("http://localhost:8080/v1/template/search",{
                headers:{
                    "Authorization":"Bearer "+localStorage.getItem("token").trim()
                },params:{
                    keyWord:searchKeyword
                }
            }).then(res=>{
                setData(res.data);
            })
        }
    },[searchKeyword,currentTab,tabIndex])
    


    return(
        <React.Fragment>
        <div className="page-content">
            <Container fluid>
                <Row>
                    <Col lg="12">
                        <div >
                            <div>
                                <div className="d-f j-c-s-b center-align">
                                    <Input
                                        type="text"
                                        value={searchKeyword}
                                        className="form-input-long form-input-any small-height"
                                        // width="200px"
                                        style={{width:"200px"}}
                                        onChange={(e) => { setSearchKeyword(e.target.value) }}
                                        maxLength="225"
                                        rows="3"
                                        placeholder="Keyword"
                                    />
                                    <UncontrolledDropdown>
                                        <DropdownToggle href="#" className="card-drop" tag="i">
                                            <img width="20px" alt="more" src={more}/>
                                        </DropdownToggle>
                                        <DropdownMenu right>
                                            <DropdownItem onClick={()=>toggleModal(true)} href="#">Add Bill</DropdownItem>
                                            <DropdownItem onClick={()=>toggleModal(false)} href="#">Add Text</DropdownItem>
                                        </DropdownMenu>
                                    </UncontrolledDropdown>
                                </div>
                                <table className=" table-responsive project-list-table table-nowrap table-centered table-borderless" style={{width:"100%"}}>
                                    <thead className="table-body">
                                        <tr>
                                            <th scope="col" >Template Text</th>
                                            <th scope="col">Labels</th>
                                            <th scope="col">Keyword</th>
                                            <th scope="col">Type</th>
                                            <th scope="col">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody className="table-body">
                                        {data.map((item,index)=>
                                            <tr>
                                            <td>
                                                <p className="mb-0">{item.templateText}</p>
                                            </td>
                                            <td>
                                                {item.labels.map(it=><span className="badge badge-tag tag-item">{it}</span>)}
                                            </td>
                                            <td>{item.keyword}</td>
                                            <td><span className={item.templateTypeEnum==="BILL_TEMPLATE" ? " badge badge-primary" : " badge badge-yellow"}>{item.templateTypeEnum}</span></td>
                                            <td>
                                                <UncontrolledDropdown>
                                                    <DropdownToggle href="#" className="card-drop" tag="i">
                                                        <img width="20px" alt="more" src={more}/>
                                                    </DropdownToggle>
                                                    <DropdownMenu right>
                                                        <DropdownItem onClick={()=>removeItem(item)} href="#">remove</DropdownItem>
                                                        <DropdownItem onClick={()=>editItem(item)} href="#">edit</DropdownItem>
                                                    </DropdownMenu>
                                                </UncontrolledDropdown>
                                            </td>
                                        </tr>
                                        )}
                                         
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </Col>
                </Row>


            </Container>
            <AddTemplateModal
                params={addTemplateData}
                fetchData={fetchData}
                handleVisible={toggleModal}/>
            </div>
            </React.Fragment>
    )
}

        
export default TemplateTable;
