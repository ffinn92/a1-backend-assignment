import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

function EditPostItem() {
  const { postId } = useParams();
  const [post, setPost] = useState();

  async function requestPost(postId) {
    const GET_POST_URL = `https://a15c8e94-d677-49fb-910f-e2d7bafbd878.mock.pstmn.io/api/post/${postId}`;
    const response = await fetch(GET_POST_URL, {
      method: "GET",
    });
    const data = await response.json();
    setPost(data);
  }

  async function updatePost(postId) {
    const UPDATE_POST_URL = `https://a15c8e94-d677-49fb-910f-e2d7bafbd878.mock.pstmn.io/api/post/${postId}`;
    const body = JSON.stringify(post);
    const response = await fetch(UPDATE_POST_URL, {
      method: "POST",
      body: body,
    });
    const data = await response.json();
    setPost(data);
    window.alert(`${postId}번 게시물을 수정했습니다.`);
  }

  useEffect(() => {
    requestPost(postId);
  }, []);

  return (
    <div style={{ width: "100%", height: "100%" }}>
      <p style={{ border: "1px solid black" }}>
        titile:{" "}
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
          checked={post?.isChecked}
          onChange={() => {
            setPost((prev) => {
              return { ...prev, isChecked: !prev.isChecked };
            });
          }}
        />
        <label for="priority">중요 게시물 여부</label>
      </div>
      <button style={{ height: "30px" }} onClick={() => updatePost(postId)}>
        확인
      </button>
    </div>
  );
}

export default EditPostItem;
