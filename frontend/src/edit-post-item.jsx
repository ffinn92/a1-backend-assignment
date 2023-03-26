import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

function EditPostItem() {
  const { postId } = useParams();
  const [post, setPost] = useState();

  async function requestPost(postId) {
    const GET_POST_URL = `http://localhost:8080/api/posts/${postId}`;
    const response = await fetch(GET_POST_URL, {
      method: "GET",
    });
    const data = await response.json();
    setPost(data);
  }

  async function updatePost() {
    const UPDATE_POST_URL = `http://localhost:8080/api/posts`;
    const body = JSON.stringify(post);
    const response = await fetch(UPDATE_POST_URL, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json"
      },
      body: body,
    });
    const data = await response.json();
    setPost(data);
    window.alert(`게시물을 수정했습니다. 뒤로가기를 눌러주세요.`);
  }

  useEffect(() => {
    requestPost(postId);
  }, []);

  return (
    <div style={{ width: "100%", height: "100%" }}>
      <p style={{ border: "1px solid black" }}>
        title:{" "}
        <input
          value={post?.title}
          onChange={(e) =>
            setPost((prev) => {
              return { ...prev, title: e.target.value };
            })
          }
        />
      </p>
      <p style={{ border: "1px solid black" }}>nickname:{post?.nickname}</p>
      <p style={{ border: "1px solid black" }}>
        content:{" "}
        <input
          value={post?.content}
          onChange={(e) =>
            setPost((prev) => {
              return { ...prev, content: e.target.value };
            })
          }
        />
      </p>
      <div
        style={{
          display: "flex",
          gap: "10px",
          alignItems: "center",
          border: "1px solid grey",
        }}
      >
        <input
          type="checkbox"
          id="priority"
          name="priority"
          checked={post?.priority}
          onChange={() => {
            setPost((prev) => {
              return { ...prev, priority: !prev.priority };
            });
          }}
        />
        <label for="priority">중요 게시물 여부</label>
      </div>
      <button style={{ height: "30px" }} onClick={() => updatePost()}>
        확인
      </button>
    </div>
  );
}

export default EditPostItem;
