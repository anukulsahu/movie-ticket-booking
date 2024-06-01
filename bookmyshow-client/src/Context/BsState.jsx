import React, { useState, useEffect } from "react";
import BsContext from "./BsContext";

const BsState = (props) => {
  const [time, changeTime] = useState(""); // time slot which the user selects.
  const [movie, changeMovie] = useState(""); // Movie which the user selects.
  const [lastBookingDatas, setLastBookingData] = useState(null); // Last movie booking details.
  const [noOfSeat, changeNoOfSeats] = useState({
    A1: 0,
    A2: 0,
    A3: 0,
    A4: 0,
    D1: 0,
    D2: 0,
  }); // No of seats which the user selects.

  // handling post request to save booking details on the backend
  const handlePostBooking = async () => {
    // Sending api request to backend with user selected movie, slot and seats to book movie.
    try {
      const res = await fetch(`http://localhost:9090/booking/add`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ movie: movie, slot: time, seats: noOfSeat }),
      });
      const data = await res.json();
      console.log("data= ", data);
      if (res.status === 200) {
        //reset the state on success

        console.log("Success motherfucker!!");
        changeTime("");
        changeMovie("");
        changeNoOfSeats({
          A1: 0,
          A2: 0,
          A3: 0,
          A4: 0,
          D1: 0,
          D2: 0,
        });
        setLastBookingData(data.data);
        //clearing the local storage when booking is successfull
        window.localStorage.clear();
      }
    } catch (error) {
      console.log("Failure in post motherfuckerr!");
      console.log("error", error);
      return false;
    }
    //message from backend on popup on UI whether success or error
  };

  // useEffect(() => {
  //   //Taking movies, slot and seats from localstorage and updating state (useful if page refreshes)
  //   const movie = window.localStorage.getItem("movie");
  //   const slot = window.localStorage.getItem("slot");
  //   const seats = JSON.parse(window.localStorage.getItem("seats"));

  //   if (movie || slot || seats) {
  //     changeMovie(movie);
  //     changeTime(slot);
  //     changeNoOfSeats(seats);
  //   }
  // }, []);

  return (
    // provider for BsContext
    <BsContext.Provider
      value={{
        handlePostBooking,
        movie,
        changeMovie,
        time,
        changeTime,
        noOfSeat,
        changeNoOfSeats,
        lastBookingDatas,
      }}
    >
      {props.children}
    </BsContext.Provider>
  );
};

export default BsState;
