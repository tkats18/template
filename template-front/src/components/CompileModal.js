import { Modal , ModalBody, ModalHeader} from "reactstrap"
import "../assets/css/Compile.css"
import close from "./../assets/images/close.png"

const CompileModal = ({params,handleVisible})=>{
    return(
        <Modal 
            isOpen={params && params.isOpen}
            toggle={() => handleVisible()}
            className="confirm-modal"
        >
            <ModalHeader>
                <div className="d-f j-c-s-b">
                    <spam>
                        Compiled Template
                    </spam>
                    <img alt="add template" src={close} className="modal-header-img" onClick={()=>handleVisible()}/>
                </div>
            </ModalHeader>
            <ModalBody className="full-height">
                <div className="center full-width full-height">
                    {params && params.isOpen && 
                        <>
                            {params.data.bill && <div className="compile-bill">
                                <table>
                                    <tbody>
                                        <tr>
                                            <td className="inner-table-header">
                                            {params.data.header}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <table className="inner-table">
                                                    <tbody>
                                                        {Object.keys(params.data.billParams).map((val,index)=>(
                                                            <tr className="compile-inner">
                                                                <td>
                                                                    {val}
                                                                </td>
                                                                <td className="end-text">
                                                                    {params.data.billParams[val]}
                                                                </td>
                                                            </tr>
                                                            ))
                                                        }
                                                         
                                                    </tbody>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr className="inner-footer">
                                            <td>
                                                Skote Inc. 2896 Howell Rd, Russellville, AR, 72823
                                            </td>
                                        </tr>
                                        <tr className="inner-footer">
                                            <td>
                                                @2021 tkats18
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>}
                        
                            {!params.data.bill && <p className="center">{params.data.header}</p>}
                        
                        </>
                    }
                </div>
            </ModalBody>

    </Modal>
    )
}

export default CompileModal;