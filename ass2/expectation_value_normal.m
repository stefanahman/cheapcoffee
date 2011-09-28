function [my,sigma_squared] = expectation_value_normal(list)
    my = mean(list);
    sigma_squared = var(list);
end