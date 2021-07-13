import axios from "axios";
import React, { useEffect, useState }  from "react"
import {Card} from "reactstrap"
import {
    Sparklines,
    SparklinesLine,
    SparklinesSpots,
  } from "react-sparklines";


const StatisticGraph=({currentTab,tabIndex})=>{

    const [data,setData] = useState([])

    useEffect(()=>{
        if (currentTab===tabIndex){
            axios.get("http://localhost:8080/v1/statistics", { 
                headers:{
                    "Authorization":"Bearer "+localStorage.getItem("token").trim()
                }
            }).then(res=>{
                setData(res.data)
            }).catch(error=>{
                console.log(error)
            })
        }
    },[currentTab,tabIndex])

    return (
        <Card>
            <Sparklines
                height={100}
                data={data}
                margin={6}
                >
                <SparklinesLine
                style={{
                    strokeWidth: 1,
                    stroke: "#336aff",
                    fill: "none"
                }}
                />
                <SparklinesSpots
                size={4}
                style={{
                    stroke: "#336aff",
                    strokeWidth: 1,
                    fill: "white"
                }}
                />
            </Sparklines>
        </Card>
    )
}

export default StatisticGraph;