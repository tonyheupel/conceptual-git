require_relative './spec_helper'

require 'commit_value'

describe CommitValue do
    it "should have a constructor that assigns the passed in value to the value member" do
      c = CommitValue.new 5
      c.value.must_equal 5
    end

    it "should not allow writing to the value member" do
      c = CommitValue.new 5
      -> { c.value = 10 }.must_raise NoMethodError
    end
end
