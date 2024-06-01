
import React, { useState, useEffect } from "react";
import Section from "../../components/Section/Section";
import { getAllRaces } from "../../api/race";


const Race = () => {
    
    const [data, setData] = useState(null);
    const authToken = localStorage.getItem("authToken");

    useEffect(() => {
        console.log(authToken);
        const response = getAllRaces(authToken);
        console.log(data);
        setData(response);
      }, [])
  return (
    
    <Section title="RACE APP">
    </Section>
  );
};

export default Race; 