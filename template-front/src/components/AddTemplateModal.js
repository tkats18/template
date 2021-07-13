import { useEffect, useState } from "react"
import { Modal , ModalBody, ModalHeader, Input,Button} from "reactstrap"
import "../assets/css/AddConfiguration.css"
import close from "./../assets/images/close.png"
import minus from "./../assets/images/minus.png"
import add from "./../assets/images/add.png"
import axios from "axios"

const AddTemplateModal = ({params,fetchData,handleVisible})=>{

    const [labels,setLabels] = useState([])
    const [templateText, settemplateText] = useState("")
    const [keyword, setKeyWord] = useState("")

    const resetValues=()=>{
        setLabels([])
        settemplateText("")
        setKeyWord("")
    }

    useEffect(()=>{
        if (params.editableObj!==null){
            setLabels(params.editableObj.labels);
            settemplateText(params.editableObj.templateText);
            setKeyWord(params.editableObj.keyword);
        }else{
            resetValues();
        }
    },[params])

    const resetModal=()=>{
        resetValues();
        fetchData();
        handleVisible(false)
    }
    
    const addTemplate=()=>{
        var curLabels = labels
        var textLabels = templateText.match(/[^{}]+(?=\})/g)
        if (textLabels!==null){
            textLabels.map((val)=> curLabels.push(val))
        } 

        axios.post("http://localhost:8080/v1/template", {
            templateText,
            labels:curLabels,
            keyword, 
            templateType: params.isBill ? "BILL_TEMPLATE" :"TEXT_TEMPLATE"
        }, {
            headers:{
                "Authorization":"Bearer "+localStorage.getItem("token").trim()
            }
        }).then(res=>{
            resetModal()
        })
    }
    
    const updateTemplate=()=>{
        axios.put(`http://localhost:8080/v1/template/${params.editableObj.id}`, {
            templateText,
            labels,
            keyword,
            id: params.editableObj.id,templateType: params.editableObj.templateTypeEnum ? "BILL_TEMPLATE" :"TEXT_TEMPLATE"
        }, { 
            headers:{
                "Authorization":"Bearer "+localStorage.getItem("token").trim()
            }
        }).then(res=>{
            resetModal()
        })
    }
    return(
        <Modal 
            isOpen={params && params.isOpen}
            toggle={() => handleVisible(false)}
            className="confirm-modal"
        >
            <ModalHeader>
                <div className="d-f j-c-s-b">
                    <spam>
                        Add Template
                    </spam>
                    <img alt="add template" src={close} className="modal-header-img" onClick={()=>handleVisible(false)}/>
                </div>
            </ModalHeader>
            <ModalBody>
            <div id="form" className="add-template-form">
                <div className="d-f j-c-s-b align-center medium-height" >
                    <label>
                        
                    </label>
                    <Button className="add-label-delimiter" onClick={()=>settemplateText(templateText+"[(${})]")} >
                        <img alt="add template" src={add} className="modal-header-img" onClick={()=>handleVisible(false)}/>
                        Add Delimiter In Text
                    </Button>
                </div>
                <Input
                    type="textarea"
                    id="textarea"
                    value={templateText}
                    className="form-textarea form-input-any center-margin text-area"
                    onChange={(e) => { settemplateText(e.target.value) }}
                    maxLength="225"
                    rows="3"
                    placeholder="Type the template main text here."
                    />

                <Input
                    type="text"
                    value={keyword}
                    className="form-input-long form-input-any  center-margin text-area"
                    onChange={(e) => { setKeyWord(e.target.value) }}
                    maxLength="225"
                    rows="3"
                    placeholder="Keyword"
                />

                <div className="input-list">

                 {labels.map((item,index)=>{
                     return (
                         <div className="center margin-top">
                            <Input
                                type="text"
                                value={labels[index]}
                                className="form-input-long form-input-any template-input-list"
                                maxLength="225"
                                onChange={(e)=> {
                                    var tmp = labels;
                                    tmp[index] = e.target.value;
                                    setLabels([...tmp])
                                }}
                                rows="3"
                                placeholder="label"
                            />
                            <img src={minus} className="small-icon" alt="remove" onClick={()=>{
                                setLabels(labels.filter((value,idx)=>index!==idx));
                            }} />
                        </div>
                     )
                    })}
                 <div className="center input-list-input j-c-f-e">
                 {params && params.isBill &&
                    <img src={add} className="small-icon" alt="remove" onClick={()=>{
                        var curLabels = labels
                        curLabels.push("")
                        setLabels([...curLabels])
                    }} />
                }                      

                </div>
            </div>
            </div>
                <div className="center full-width" style={{height:"20%"}}>
                     <Button
                        className="btn-success add-label center-margin"
                        onClick={()=>{
                            if (params.editableObj===null){
                                addTemplate()
                            }else{
                                updateTemplate()
                            }
                            console.log(templateText,labels,keyword)
                        }}
                        >
                        Add label
                    </Button>
                </div>
            </ModalBody>

    </Modal>
    )
}

export default AddTemplateModal;