import React,{useEffect, useState} from 'react';
import {  Nav, NavItem, NavLink, TabContent, TabPane} from "reactstrap";
import "./../assets/images/more.png"
import classnames from 'classnames';

import Configurations from "./../components/ConfigurationTable"
import TemplateTable from '../components/TemplateTable';
import StatisticGraph from "../components/Statistics"
import { useHistory } from 'react-router-dom';



const MainPage = (authorized) => {

    const isLoggedIn = localStorage.getItem("token");
    const hist = useHistory()
    const [activeTab, setactiveTab] = useState('1');
    function toggleTab(tab) {
        if (activeTab !== tab) {
          setactiveTab(tab)
        }
    }

    console.log(authorized)
    useEffect(()=>{
        if(!isLoggedIn){
            hist.push("/login")
        }
    },[isLoggedIn,hist])

    return (
        <div className="main-div">
        {authorized ? 
        <>
            <Nav pills justified>
                <NavItem>
                    <NavLink
                        className={classnames({ active: activeTab === '1' })}
                        onClick={() => { toggleTab('1'); }}
                    >
                        <i className="bx bx-chat font-size-20 d-sm-none"></i>
                        <span className="d-none d-sm-block">Filled Templates</span>
                    </NavLink>
                </NavItem>
                <NavItem>
                    <NavLink
                        className={classnames({ active: activeTab === '2' })}
                        onClick={() => { toggleTab('2'); }}
                    >
                        <i className="bx bx-group font-size-20 d-sm-none"></i>
                        <span className="d-none d-sm-block">Templates</span>
                    </NavLink>
                </NavItem>
                <NavItem>
                    <NavLink
                        className={classnames({ active: activeTab === '3' })}
                        onClick={() => { toggleTab('3'); }}
                    >
                        <i className="bx bx-book-content font-size-20 d-sm-none"></i>
                        <span className="d-none d-sm-block">Statistics</span>
                    </NavLink>
                </NavItem>
            </Nav>
            <TabContent activeTab={activeTab}>
                <TabPane  tabId="1">

                    <Configurations currentTab={activeTab} tabIndex={"1"}/>
                </TabPane>
                <TabPane  tabId="2">
                    <TemplateTable currentTab={activeTab} tabIndex={"2"}/>
                </TabPane>
                <TabPane  tabId="3">
                    <StatisticGraph currentTab={activeTab} tabIndex={"3"}/>
                </TabPane>

            </TabContent>
        </>:
            <div>Authorize first</div>
        }
        </div>

        );
    }

export default MainPage;