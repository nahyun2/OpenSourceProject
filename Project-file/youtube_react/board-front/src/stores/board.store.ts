import { create } from "zustand";

interface BoardStore {
    title: string;
    content: string;
    boardImageFileList: File[];
    setTitle: (title : string) => void;
    setContent: (content : string ) => void;
    setBoardImageFileLIst : (boardImageFileList : File[]) => void;
    resetBoard : () => void;
};

const useBoardStore = create<BoardStore>(set => ({
    title: '',
    content: '',
    boardImageFileList:[],
    setTitle: (title) => set(state=> ({ ...state, title})),
    setContent: (content) => set(state=> ({ ...state, content})),
    setBoardImageFileLIst: (boardImageFileList) => set(state=> ({ ...state, boardImageFileList})),
    resetBoard: () => set(state => ({...state, title: '', content : '', boardImageFileList: []})),
    
}));

export default useBoardStore