import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import PostList from "./post-list";
import PostItem from "./post-item";
import EditPostItem from "./edit-post-item";
import WritePostItem from "./write-post-item";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<PostList />}></Route>
          <Route path="/post/:postId" element={<PostItem />}></Route>
          <Route path="/write" element={<WritePostItem />}></Route>
          <Route path="/edit/:postId" element={<EditPostItem />}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
