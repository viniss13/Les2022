import React from 'react'

import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';

import { Line } from 'react-chartjs-2';

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
)

const UserStatsGraphs = ( {data, labels} ) => {

  console.log("DATAA USER STATS GRAPH", data)
  console.log("labels USER STATS GRAPH", labels)

  const [graph, setGraph] = React.useState([0]);

  var dataGraphs = {
    labels: labels,
    datasets: data,
    backgroundColor:[]        
}

  return (
    <section >

      <div>
        <Line 
          data={dataGraphs}
        />      
      </div>
    </section>
  )
}

export default UserStatsGraphs