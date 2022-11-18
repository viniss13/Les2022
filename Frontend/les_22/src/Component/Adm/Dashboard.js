
import React, { useEffect, useState } from "react";
import DashboardService from "../../service/Dashboard/DashboardService";
import UserStatsGraphs from "./UserStatsGraphs";

const dashboardService = new DashboardService();

let colorIndex = 0;

const colors = [
"#7FFFD4","#000000","#0000FF","#8A2BE2",
"#7FFF00",
"#00FFFF",
"#00008B",
"#FF00FF",
"#FFD700",
"#808080",
"#00FF00",
"#ADFF2F",
"#4B0082",
"#FF4500",
"#800080",
"#FF0000",
"#4569E1",
"#FFFF00",
"#80000",
"#FF1493",
"#8B008B",
"#D2691E",
"#556B2F",
"#00FF0",
"#00FA9A",
"#2F4F4F"]

const Dashboard = () => {

  const [dashboardData, setDashboardData] = React.useState(null);
  const [dashBoardFinalData, setDashBoardFinalData] = React.useState([]);
  const [dashboardLabels, setDashboardLabels] = React.useState([]);
  let testeDashboard = [];

  const isNewID = (id) => {
    for(let i = 0; i < testeDashboard.length; i++){
       if(testeDashboard[i].id == id) return false;
    }

    return true;
  }

  const getDots = (id) => {
    for(let i = 0; i < testeDashboard.length; i++){
        if(testeDashboard[i].id == id) return testeDashboard[i];
    }
  }

  const getDashboard = () => {
    dashboardService.getDashboard()
        .then(response => {
          
          console.log("data que vem", response.data)
          setDashboardData(response.data);
            
          let  myDashBoard = response.data;
          
          let myDates = [];
          for(let i = 0; i < myDashBoard.length; i++){
            var isNew = true;
            let myDate = myDashBoard[i].creationDate;

            for(let j = 0; j < myDates.length; j++){
                if(myDates[j].join(",") == myDate.join(",")){
                    isNew = false;
                }
            }

            if(isNew) myDates.push(myDate);
          }

          for(let i = 0; i < myDates.length; i++){
            myDates[i] = myDates[i][2] + "/" + myDates[i][1] + "/" + myDates[i][0];
          }          

          var myDataSet = [];

          for(let i = 0; i < myDashBoard.length; i++){
            var isNew = true;
            let formatDate = myDashBoard[i].creationDate[2] + "/" + myDashBoard[i].creationDate[1] + "/" + myDashBoard[i].creationDate[0];
            for(let j = 0; j < myDataSet.length; j++){
                if(myDataSet[j].id == myDashBoard[i].id){
                    isNew = false;
                    let myData = myDataSet[j].data;
                    myData[myDates.indexOf(formatDate)] = myDashBoard[i].quantity;

                    myDataSet[j].data = myData;
                }
            }

            if(isNew){
                let myObj = {};

                myObj.label = myDashBoard[i].name;
                myObj.id = myDashBoard[i].id;
                myObj.borderColor = colors[colorIndex++];
                myObj.backgroundColor = myObj.borderColor;

                if(colorIndex == colors.length) colorIndex = 0;
                let myData = [];
                


                for(let j = 0; j < myDates.length; j++){
                    if(formatDate == myDates[j]) myData.push(myDashBoard[i].quantity);
                    else myData.push(0);
                }

                myObj.data = myData;
                myDataSet.push(myObj);
            }
          //  if(myDashBoard[i].id)
          }

          setDashboardLabels(myDates);
          setDashBoardFinalData(myDataSet);
          
        }).catch( err => {

        })
  }

  React.useEffect(() => {
    getDashboard();
  }, []);

  return (
    <>

      <div className="container">
        <UserStatsGraphs 
          data={dashBoardFinalData} 
          labels={dashboardLabels}/>      
      </div>
    </>
  )
}

export default Dashboard