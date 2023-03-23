import { useState, useEffect } from "react";
import { Link } from "react-router-dom";

function PostList() {
  const [posts, setPosts] = useState([]);
  const [page, setPage] = useState(0);
  const [searchKeyword, setSearchKeyword] = useState("");

  async function requestPosts(page) {
    const GET_POSTS_URL = `http://a15c8e94-d677-49fb-910f-e2d7bafbd878.mock.pstmn.io/api/posts?page=${page}&size=10`;
    const response = await fetch(GET_POSTS_URL, {
      method: "GET",
    });
    const data = await response.json();
    setPosts(data.content);
    setPage(page);
  }

  async function searchPosts(title) {
    const SEARCH_POSTS_URL = `https://a15c8e94-d677-49fb-910f-e2d7bafbd878.mock.pstmn.io/api/posts?title=${title}&page=0&size=10}`;
    const response = await fetch(SEARCH_POSTS_URL, {
      method: "GET",
    });
    const data = await response.json();
    setPosts(data.content);
  }

  useEffect(() => {
    requestPosts(0);
  }, []);

  return (
    <div style={{ width: "100%", height: "100%" }}>
      <div
        style={{
          display: "flex",
          alignItems: "center",
          justifyContent: "space-between",
        }}
      >
        <div style={{ display: "flex", alignItems: "center" }}>
          <input
            placeholder="title로 검색해보세요."
            onChange={(e) => setSearchKeyword(e.target.value)}
          />
          <button
            style={{ height: "30px" }}
            onClick={() =>
              searchKeyword ? searchPosts(searchKeyword) : requestPosts(0)
            }
          >
            검색
          </button>
        </div>
        <Link to={`/write`}>
          <button
            style={{ height: "30px" }}
            onClick={() =>
              searchKeyword ? searchPosts(searchKeyword) : requestPosts(0)
            }
          >
            작성하기
          </button>
        </Link>
      </div>
      {posts?.map((post) => (
        <Link
          to={`/post/${[post.id]}`}
          key={post.id}
          style={{
            display: "flex",
            justifyContent: "space-between",
            gap: "10px",
            width: "400px",
            height: "40px",
            padding: "0 10px",
            border: "1px solid grey",
            alignItems: "center",
            backgroundColor: post.isChecked ? "lightskyblue" : "white",
            color: "black",
          }}
        >
          <p>{post.title}</p>
          <p>nickname: {post.nickname}</p>
        </Link>
      ))}
      <div
        style={{ display: "flex", justifyContent: "space-between", gap: "4px" }}
      >
        <button
          style={{ height: "30px" }}
          onClick={() => requestPosts(page - 1)}
        >
          prev page
        </button>
        <button
          style={{ height: "30px" }}
          onClick={() => requestPosts(page + 1)}
        >
          next page
        </button>
      </div>
    </div>
  );
}

export default PostList;
