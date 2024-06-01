import React, { useContext } from "react";
import { moviesList } from "../data";
import "../styles/selectMovie.css";
import RadioButton from "./RadioButton";
import BsContext from "../Context/BsContext";
function SelectMovieButton() {
  // const [movie, changeMovie] = useState("");
  const context = useContext(BsContext);
  const { movie, changeMovie } = context;

  const handleChangeMovie = (value) => {
    changeMovie(value);
  };
  return (
    <>
      <h1 className="SM_heading">Select a Movie :-</h1>
      <div className="SM_main_container">
        {moviesList.map((el, index) => {
          return (
            <RadioButton
              text={el}
              changeSelection={handleChangeMovie}
              data={movie}
              key={index}
            />
          );
        })}
      </div>
    </>
  );
}

export default SelectMovieButton;
