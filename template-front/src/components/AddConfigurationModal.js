import { useEffect, useState } from "react"
import Select from "react-select";
import { Modal , ModalBody, ModalHeader, Input,Button} from "reactstrap"
import "../assets/css/AddConfiguration.css"
import close from "./../assets/images/close.png"
import axios from "axios"

const AddConfigurationModal = ({params,fetchData,handleVisible})=>{

    const [email,setEmail] = useState("")
    const [chosenTemplate,setChosenTemplate] = useState("")
    const [data,setData] = useState([])
    const [options,setOptions] = useState([])

    const resetModal=()=>{
        setEmail("")
        setChosenTemplate("")
        setData([])
        setOptions([])
        fetchData()
        handleVisible()
    }
    
    const addTemplate=()=>{
        var map ={}
        Object.keys(data).map((value,index) => {
            map[data[value].label] = data[value].value
            return null
        })

        axios.post("http://localhost:8080/v1/filled-template", {
            data:map,
            templateId:chosenTemplate.id,
            email:email
        }, {
            headers:{
                "Authorization":"Bearer " + localStorage.getItem("token").trim()
            }
        }).then(res=>{
            resetModal()
        })
    }

    useEffect(()=>{
        if (chosenTemplate.labels){
            var newData = []
            Object.keys(chosenTemplate.labels).map((value,index) => {
                newData=[...newData,{"label":chosenTemplate.labels[value],"value":""}]
                return null
            })

            setData([...newData])
    
        }
    },[chosenTemplate])

    useEffect(()=>{
        if (params.list){
            var newData= []
            Object.keys(params.list).map((value,index) => {
                newData=[...newData,{"label":params.list[value].keyword,"value":params.list[value]}]
                return null
            })
            setOptions(newData)
        }
    },[params.list])


    return(
        <Modal 
            isOpen={params && params.isOpen}
            toggle={() => handleVisible()}
            className="confirm-modal"
        >
            <ModalHeader>
                <div className="d-f j-c-s-b align-center">
                    <span>
                        Add Template
                    </span>
                    <img alt="add template" src={close} className="modal-header-img" onClick={()=>resetModal()}/>
                </div>
            </ModalHeader>
            <ModalBody>
            <div id="form" className="add-template-form">
                <Input
                    type="text"
                    value={email}
                    className="form-input-long form-input-any center-margin text-area"
                    width="100%"
                    onChange={(e) => { setEmail(e.target.value) }}
                    maxLength="225"
                    rows="3"
                    placeholder="Email"
                />
                <div className="center-margin">
                    <Select
                        options={options}
                        onChange={(e)=>setChosenTemplate(e.value)}
                    />
                </div>
                <div className="input-list">

                 {data.map((item,index)=>{
                     return (
                         <div className="center full-width">

                             <p>{item.label}</p>
                            <Input
                                type="text"
                                value={data[index].value}
                                className="form-input-long form-input-any input-list-input"
                                maxLength="225"
                                onChange={(e)=> {
                                    var tmp = data;
                                    tmp[index].value = e.target.value;
                                    
                                    setData([...tmp])
                                }}
                                rows="3"
                                placeholder="label"
                            />

                        </div>
                     )
                    })}
                  

                </div>
            </div>
                <div className="center full-width">
                     <Button
                        className="btn-success center-margin"
                        onClick={()=>{ addTemplate() }}
                    >
                        Add label
                    </Button>
                </div>
            </ModalBody>

    </Modal>
    )
}

export default AddConfigurationModal;