import { defineStore } from 'pinia';
import APIService from '@/services/ApiService';
import {ref} from "vue";

export const useNoteStore = defineStore('note', () => {
        const notes = ref([]);
        const error = ref(null);

        const fetchNotes = async () => {
            try {
                const response = await APIService.get('/notes');

                notes.value = response.data;

            } catch (error) {
               error.value = 'Failed to load notes';
            }
        }

        const editNote = async (noteData) => {
            try {
                await APIService.put(`/notes/${noteData.id}`, noteData);
                const index = notes.value.findIndex((note) => note.id === noteData.id);
                notes.value[index] = noteData;

            } catch (error) {
                error.value = `Failed to load note with id: ${id}`;
            }
        }

        const createNote = async (noteData) => {
            try {
                console.log(noteData.value);
                await APIService.post('/notes', noteData);
                notes.value.push(noteData);
                console.log(noteData.value);
            } catch (error) {
                error.value = 'Failed to create note';
            }
        }

        const deleteNote = async (id) => {
            try {
                await APIService.delete(`/notes/${id}`);
                notes.value = notes.value.filter((note) => note.id !== id);
            } catch (error) {
                error.value = `Failed to delete note with id: ${id}`;
            }
        }


return {error, notes, fetchNotes, createNote, deleteNote,editNote}


}

    )
