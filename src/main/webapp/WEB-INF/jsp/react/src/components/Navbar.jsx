import React from 'react';
import Requests from './Requests'
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSmileBeam, faUserFriends} from "@fortawesome/free-solid-svg-icons";
import Axios from 'axios';
import NewCommentNotification from "./NewCommentNotification";
import {useHistory, Link} from "react-router-dom";
import DataServices from "../services/DataServices";
import '../css/notifications.css';
import GlobalContext from '../contexts/GlobalContext';

let results = ["Test DATA 001"];
let currentUser = {};
let userId = 0;

export const ResultsContext = React.createContext({});
export const CurrentUserContext = React.createContext({});

export const ResultsProvider = (props) => {

    return (
        <ResultsContext.Provider value={results}>
            {props.children}
            {/* this indicates that the global store is accessible to all the child tags with MyProvider as Parent */}
        </ResultsContext.Provider>
    );
};

export const CurrentUserProvider = (props) => {
    return (
        <CurrentUserContext.Provider value={currentUser}>
            {props.children}
        </CurrentUserContext.Provider>
    );
};

const style = {
    objectFit: 'cover',
    borderRadius: '50%',
    width: '40px',
    height: '40px'
};

const style2 = {
    "float": "right",
    "margin-right": "12px"
};

function Navbar() {

    const [username, setUsername] = React.useState(" ");
    const [userId, setUserId] = React.useState(0);
    const [friendReq, setFriendReq] = React.useState([]);
    const [updateValue, setUpdateValue] = React.useState(0);
    const [searchResults, setSearchResults] = React.useState([]);
    const [newComments, setNewComments] = React.useState([]);
    const history = useHistory();
    const {authenticatedUser, setAuthenticatedUser} = React.useContext(GlobalContext);

    function getCurrentUser() {

        fetch("/userDetails", {
            method: 'GET',
            credentials: "include"
        })
            .then(response => response.json())
            .then(data => {
                setUsername(data.firstName + " " + data.lastName);
                currentUser = data;
                setAuthenticatedUser(data);
                setUserId(data.userId);
            });
    }

    function getNewComments() {
        Axios.get('/post/getNewComments')
            .then((response) => {
                setNewComments(response.data);
            }).catch((err) => {console.log(err)});
    }

    React.useEffect(() => {
        getCurrentUser();
        getAllRequests();
        getNewComments();
        // saveCurrentUserId()
    }, [userId, updateValue]);


    // const saveCurrentUserId = () => {
    //     console.log(userId)
    //     history.push({
    //         path: "/profileAll",
    //         currentUserId: {id: 3}
    //     });
    // }

    function fetchUsers(evt) {
        evt.preventDefault();

        if(evt.target.search.value)
        fetch("/searchUsers",
            {
                method: 'GET',
                credentials: "include",
                headers: {
                    'input': evt.target.search.value
                },
            })
            .then(response => response.json())
            .then(data => {
                setSearchResults(data);
                results = data;
                console.log(data);
                console.log(currentUser);
            });
        evt.target.search.value = "";
    }

    function getAllRequests() {
        if (userId) {
            DataServices.getPendingRequests(userId).then(
                response => {
                    setFriendReq(response.data);
                }
            ).catch(error => {
                console.log("No friend requests" + error.response)
            });
        }
    }

    const isInitialMount = React.useRef(true);
    React.useEffect(() => {
        if (isInitialMount.current) {
            isInitialMount.current = false;
        } else {
            history.push("/results");
        }
    }, [searchResults]);

    const stopDefault = e => {
        e.preventDefault();
        e.stopPropagation();
    }

    return (
        <>
            <nav className="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">

                <button className="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>

                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <form className="form-inline my-2 my-lg-0 mr-auto" onSubmit={fetchUsers}>
                        <input id="searchBar" className="form-control autocomplete mr-sm-2" name="search" type="search"
                               placeholder="Search" aria-label="Search"/>
                    </form>
                    {/*<div style={{width: "400px"}}></div>*/}
                    <ul className="navbar-nav">
                        <li className="nav-item active">
                            <Link to="/" className="nav-link">Feed</Link>
                        </li>
                        <li className="nav-item dropdown">
                            {((friendReq.length > 0) || (newComments.length > 0)) &&
                                <span className="badge badge-pill badge-primary"
                                  style={style2}>{friendReq.length + newComments.length}</span>
                            }
                            <a className="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Notifications
                            </a>
                            <div className="dropdown-menu dropdown-menu-right notificationsDiv" onClick={stopDefault} aria-labelledby="navbarDropdown">
                                { (friendReq.length == 0 && newComments.length == 0) &&
                                    <small className="ml-2">No New Notifications</small>
                                }
                                    {friendReq.length > 0 && <small className="text-left ml-3">Connection Requests</small>}
                                {friendReq.length !==0 &&
                                    friendReq.map((item) => (
                                        <div onClick={stopDefault} className="dropdown-item px-1 py-0">
                                            <Requests requesterInfo={{
                                                myId: userId,
                                                id: item.userId,
                                                username: item.firstName + " " + item.lastName
                                            }} handleUpdate={setUpdateValue}/>
                                        </div>
                                    ))}
                                {newComments.length > 0 && <small className="text-left ml-3">New Comments on your Posts</small>}
                                {newComments.length > 0 &&
                                    newComments.map((item) => (
                                        <div className="dropdown-item px-2 py-0 m-0">
                                            <NewCommentNotification comment={item} updateNotifications={getNewComments}/>
                                        </div>
                                    ))
                                }

                            </div>
                        </li>
                        <li className="nav-item active">
                        <Link to="/chat" className="nav-link">Messages</Link>                           
                        </li>
                        <li className="nav-item active">
                            <Link to="/connections" className="nav-link">Friends</Link>
                        </li>
                        <li>
                            <img style={style} src="/api/profile/image/download"
                                 className="avatar rounded-circle ml-3"
                                 alt="Cinque Terre"/>
                        </li>
                        <li className="nav-item">
                            <Link to="/profile" className="nav-link">{username}</Link>
                        </li>

                        <li>
                            <a className="nav-link" href="/logout">Logout</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </>
    );
}

export default Navbar;
