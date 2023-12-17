import React from 'react';
import { render, fireEvent, waitFor } from '@testing-library/react';
import { describe, test, expect } from 'vitest'
import Register from "../pages/register/register";

describe('Register', () => {
    it('registers a user with valid data', async () => {
      // Arrange: Render the register component
      const { getByLabelText, getByText } = render(<Register />);
  
      // Act: Simulate user input and form submission
      const emailInput = getByLabelText('Email:');
      const passwordInput = getByLabelText('Password:');
      const submitButton = getByText('Submit');
    //simulates click of button by changing and firing event
      fireEvent.change(emailInput, { target: { value: 'test@test1.com' } });
      fireEvent.change(passwordInput, { target: { value: 'testpassword' } });
      fireEvent.click(submitButton);
  
      // Assert: Check if the registration message is displayed
      await waitFor(() => {
        const registrationMessage = getByText('Registration successful');
        expect(registrationMessage).toBe('Registration successful');
      });
    });
    
  });