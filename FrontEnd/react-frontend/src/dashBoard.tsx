import { useState } from "react";
import axios from "axios"; 
function DashBoard() {

    async function UserInfor(event: React.MouseEvent<HTMLButtonElement, MouseEvent>) {
        event.preventDefault();


    const [userData, setUser] = useState("");


    try {
          await axios.get("http://localhost:8081/api/user_infor").then((Response) => {
            setUser(Response.data);
          }); 

    } catch (err) {
        alert(err);
      }
    }


    return(
        <div>
            <button type="submit" onClick={(event) => UserInfor(event)}>Click</button>

            <h2></h2>
            <h2></h2>
            <h2></h2>
            <h2></h2>
        </div>
    );
}

export default DashBoard;